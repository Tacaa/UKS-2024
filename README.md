# UKS-2024
This repository was created for the purposes of the software configuration management course. The subject is developing an application that is a simpler version of Dockerhub.

Prerequisites:
    Make sure you have the following tools installed:
        Visual Studio Code
        Git
        Docker
        PgAdmin
        PostgreSQL Database


PgAdmin Connection Settings:
    To connect to the PostgreSQL database through PgAdmin, use the following configuration:
        Host name/address: localhost
        Port: 5434
        Maintenance database: uks_db
        Username: postgres
        Password: postgres


Clone the Project:
    git clone <REPOSITORY_URL>


Run the Application with Docker:
Navigate to the project root and run:
    docker compose up --build


Access the Application:
    http://localhost:4200


Super Admin Login:
    To log in as the super admin:
        Username: superadmin
        Password:
            Open the Docker container named uks-backend
            Locate the file at: app/log/uks-super-admin.txt
            Copy the password from that file.
            Paste that password into the Generated Password field.
            Set your new password.


Login of regular user:
    Username: johndoe
    Password: 123
