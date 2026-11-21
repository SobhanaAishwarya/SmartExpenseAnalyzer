pipeline {
  agent any
  options { timestamps(); disableConcurrentBuilds() }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Clean and compile') { steps { bat 'mvn -B clean compile' } }
    stage('Test') { steps { bat 'mvn -B test' } }
    stage('Package and verify') { steps { bat 'mvn -B package' } }
    stage('Docker image') {
      when { expression { fileExists('Dockerfile') } }
      steps { script {
        def available = bat(returnStatus: true, script: 'docker version')
        if (available == 0) { bat 'docker build -t smart-expense-analyzer:%BUILD_NUMBER% .' }
        else { echo 'Docker CLI or daemon unavailable; image stage skipped.' }
      } }
    }
  }
  post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'; archiveArtifacts artifacts: 'target/*.jar', fingerprint: true } }
}
