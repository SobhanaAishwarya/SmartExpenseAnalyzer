pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'CI Pipeline completed successfully!'
            emailext(
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "The Jenkins CI pipeline completed successfully.",
                to: "aishwaryasobhana@gmail.com"
            )
        }
        failure {
            echo 'CI Pipeline failed!'
            emailext(
                subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "The Jenkins CI pipeline failed. Please check the console output.",
                to: "aishwaryasobhana@gmail.com"
            )
        }
    }
}
