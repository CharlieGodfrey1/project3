resource "aws_instance" "bastion" {
  ami               = var.ami_id
  instance_type     = "t2.small"
  availability_zone = var.av-zone
  key_name          = var.pem-key

  network_interface {
    device_index         = 0
    network_interface_id = var.network_interface4_id
  }
  depends_on = [
    aws_db_instance.taurusdb
  ]

  user_data = <<-EOF
                #!/bin/bash
                sudo apt install mysql-client-core-8.0
                EOF

  tags = {
    Name = "bastion"
  }
}

resource "null_resource" "remoteExecProvisionerBastion" {
  depends_on = [
    var.inventory_yaml,
  ]

  provisioner "file" {
    source      = "C:/Users/local_admin/.ssh/jenkins-key.pem"
    destination = "~/.ssh/jenkins-key.pem"
  }

  provisioner "file" {
    source      = "C:/Users/local_admin/.ssh/prod-key.pem"
    destination = "~/.ssh/prod-key.pem"
  }

  provisioner "file" {
    source      = "./ansible-role"
    destination = "~/ansible-role"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt update -y",
      "sudo apt install software-properties-common",
      "sudo apt-add-repository --yes --update ppa:ansible/ansible",
      "sudo apt install ansible -y"
    ]
  }

  provisioner "remote-exec" {
    inline = [
      "sudo chmod 400 ~/.ssh/jenkins-key.pem",
      "sudo chmod 400 ~/.ssh/prod-key.pem"
    ]
  }

  provisioner "remote-exec" {
    inline = [
      "cd ansible-role && ansible-playbook -i inventory.yaml playbook.yaml"
    ]
  }

  connection {
    host        = aws_instance.bastion.public_ip
    private_key = file("~/.ssh/aws-project-1.pem")
    user        = "ubuntu"
    type        = "ssh"
    agent       = "false"
  }
}

resource "aws_instance" "jenkins" {
  ami               = var.ami_id
  instance_type     = "t2.medium"
  availability_zone = var.av-zone

  network_interface {
    device_index         = 0
    network_interface_id = var.network_interface1_id
  }

  key_name  = var.jenkins-key
  user_data = <<-EOF
                  #!/bin/bash
                  sudo su && export PROD=${aws_instance.prod_vm.private_ip} >>/home/jenkins/.bashrc                
                  sudo su && export PROD=${aws_instance.prod_vm.private_ip} >>/home/jenkins/.profile                
                  sudo su && export PROD=${aws_instance.prod_vm.private_ip} >>/etc/environment
                  sudo su && export TEST=${aws_instance.test_vm.private_ip} >>/home/jenkins/.bashrc                
                  sudo su && export TEST=${aws_instance.test_vm.private_ip} >>/home/jenkins/.profile                
                  sudo su && export TEST=${aws_instance.test_vm.private_ip} >>/etc/environment               
                  source ~/.bashrc                
                  source ~/.profile        

  EOF

    tags = {
    Name = "jenkins"
  }
}

resource "null_resource" "remoteExecProvisionerJenkins" {
  depends_on = [
    var.inventory_yaml,
  ]

  provisioner "file" {
    source      = "C:/Users/local_admin/.ssh/prod-key.pem"
    destination = "~/.ssh/prod-key.pem"
  }

  provisioner "remote-exec" {
  inline = [
    "sudo chmod 400 ~/.ssh/prod-key.pem"
  ]
  }

  connection {
    host        = aws_instance.jenkins.public_ip
    private_key = file("~/.ssh/jenkins-key.pem")
    user        = "ubuntu"
    type        = "ssh"
    agent       = "false"
  }
}

resource "aws_instance" "prod_vm" {
  ami               = var.ami_id
  instance_type     = "t2.medium"
  availability_zone = var.av-zone
  key_name          = var.prod-key

  network_interface {
    device_index         = 0
    network_interface_id = var.network_interface2_id
  }

  tags = {
    Name = "prod-vm"
  }
}

resource "aws_instance" "test_vm" {
  ami               = var.ami_id
  instance_type     = "t2.medium"
  availability_zone = var.av-zone
  key_name          = var.prod-key

  network_interface {
    device_index         = 0
    network_interface_id = var.network_interface3_id
  }

  tags = {
    Name = "test-vm"
  }
}

resource "aws_db_instance" "taurusdb" {
  identifier                = "taurusdb"
  name                      = "taurusdb"
  allocated_storage         = 10
  db_subnet_group_name      = var.db_subnet_group
  vpc_security_group_ids    = [var.bastion_security_group_id, var.deploy_security_group_id, var.db_security_group_id]
  engine                    = "mysql"
  engine_version            = "5.7"
  instance_class            = "db.t3.micro"
  username                  = "admin"
  password                  = var.db_password
  parameter_group_name      = "default.mysql5.7"
  skip_final_snapshot       = true
  publicly_accessible       = false
  final_snapshot_identifier = "taurusdb-final-snapshot-2"
  snapshot_identifier       = "taurusdb-final-snapshot-2"
}
