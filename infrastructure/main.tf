provider "aws" {
  region     = "eu-west-1"
  access_key = var.access_key
  secret_key = var.secret_key
}

module "EC2" {
  source = "./EC2"

  ami_id                    = module.VPC.ami_id
  pem-key                   = var.pem-key
  jenkins-key               = var.jenkins-key
  prod-key                  = var.prod-key
  test-key                  = var.test-key
  network_interface1_id     = module.Subnet.Network_interface1_id
  network_interface2_id     = module.Subnet.Network_interface2_id
  network_interface3_id     = module.Subnet.Network_interface3_id
  network_interface4_id     = module.Subnet.Network_interface4_id
  db_subnet_group           = module.Subnet.db_subnet_group
  av-zone                   = "eu-west-1a"
  bastion_security_group_id = module.VPC.bastion_security_group_id
  deploy_security_group_id  = module.VPC.deploy_security_group_id
  db_password               = var.db_password
  db_security_group_id      = module.VPC.db_security_group_id
  inventory_yaml            = local_file.tf_ansible_inventory

}

module "VPC" {
  bastion_ip = module.EC2.Bastion_id
  jenkins_ip = module.EC2.Jenkins_id
  source     = "./VPC"
}

module "Subnet" {
  source = "./Subnet"

  vpc_id                    = module.VPC.vpc_id
  bastion_security_group_id = module.VPC.bastion_security_group_id
  jenkins_security_group_id = module.VPC.jenkins_security_group_id
  deploy_security_group_id  = module.VPC.deploy_security_group_id
  aws_route_id              = module.VPC.aws_route_id
  internet_gate             = module.VPC.internet_gate
}

resource "local_file" "tf_ansible_inventory" {
  content  = <<-DOC
all:
  vars:
      ansible_ssh_common_args: '-o StrictHostKeyChecking=no'
      ansible_user: ubuntu
  children:
    jenkins:
      vars:
        ansible_ssh_private_key_file: '~/.ssh/jenkins-key.pem'
      hosts:
        ${module.EC2.Jenkins_id}:
    prod:
      vars:
        ansible_ssh_private_key_file: '~/.ssh/prod-key.pem'
      hosts:
        ${module.EC2.Prod_id}:
    test:
      vars:
        ansible_ssh_private_key_file: '~/.ssh/prod-key.pem'
      hosts:
        ${module.EC2.Test_id}:
    DOC
  filename = "./ansible-role/inventory.yaml"
}

resource "local_file" "tf_docker-compose" {
  content  = <<-DOC
version: "3.8"
services:
  nginx:
    image: nginx:alpine
    ports:
    - target: 80
      published: 80
      protocol: tcp
    volumes:
    - type: bind
      source: ./nginx/nginx.conf
      target: /etc/nginx/nginx.conf 


  frontend:
    container_name: frontend
    image: tmarshfsr/frontend
    build: ./frontendproject
    ports: 
      - target: 3000
        published: 3000

  backend:
    container_name: backend
    image: tmarshfsr/backend
    build: ./backend
    ports: 
      - target: 8080
        published: 8080
    build: ./TaurusBackend

    DOC
  filename = "../docker-compose.yaml"
}
