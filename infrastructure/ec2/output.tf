output "Bastion_id" {
  value = aws_instance.bastion.public_ip
}
output "Prod_id" {
  value = aws_instance.prod_vm.private_ip
}
output "Test_id" {
  value = aws_instance.test_vm.private_ip
}
output "Jenkins_id" {
  value = aws_instance.jenkins.private_ip
}

output "MySQLEndPoint" {
  value = aws_db_instance.taurusdb.address
}