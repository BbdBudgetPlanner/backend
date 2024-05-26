variable "db_password" {
  type        = string
  description = "The password for the database."
  sensitive   = true
  default     = "${{ secrets.DB_PASSWORD }}"
}

