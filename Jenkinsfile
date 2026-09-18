pipeline {

    agent any

    tools {
        maven 'Maven-3'
    }

    stages {

        stage('Checkout') {

            steps {

                checkout scm

            }

        }

        stage('Build') {

            steps {

                sh 'mvn clean package'

            }

        }

        stage('Test') {

            steps {

                sh 'mvn test'

            }

        }

        stage('Archive Artifact') {

            steps {

                archiveArtifacts artifacts: 'target/*.jar',
                    fingerprint: true

            }

        }

    }

}