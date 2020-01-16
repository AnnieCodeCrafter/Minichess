pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Testing') {
      steps {
        sh 'mvn test'
      }
    }

  }
}