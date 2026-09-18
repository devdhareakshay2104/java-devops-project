pipeline {

    agent any

    tools {
        maven 'Maven-3'
    }

    environment {
        ANSIBLE_SERVER = '13.235.70.39'
        ANSIBLE_USER = 'ubuntu'
        SSH_KEY = '/var/lib/jenkins/.ssh/id_rsa'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java Maven application...'
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo 'Archiving Maven JAR...'
                archiveArtifacts artifacts: 'target/*.jar',
                    fingerprint: true
            }
        }

        stage('Transfer JAR to Ansible') {
            steps {
                echo 'Transferring JAR to Ansible Server...'

                sh '''
                    scp -o StrictHostKeyChecking=no \
                        -i "$SSH_KEY" \
                        target/*.jar \
                        ${ANSIBLE_USER}@${ANSIBLE_SERVER}:/home/ubuntu/java-devops-project.jar
                '''
            }
        }

        stage('Deploy using Ansible') {
            steps {
                echo 'Running Ansible deployment...'

                sh '''
                    ssh -o StrictHostKeyChecking=no \
                        -i "$SSH_KEY" \
                        ${ANSIBLE_USER}@${ANSIBLE_SERVER} \
                        "ansible-playbook -i /home/ubuntu/inventory /home/ubuntu/deploy.yml"
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline completed successfully!'
        }

        failure {
            echo 'CI/CD Pipeline failed. Check the Console Output.'
        }
    }
}