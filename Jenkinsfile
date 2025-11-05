pipeline {
  agent any 
  tools {
    maven 'maven-3.9'
  }
  stages {
    // building stage
    stage ("build jar"){
      steps {
        script {
          echo "building the application"
          sh 'mvn package'
        }
      }
    }

    // building stage
    stage ("build"){
      steps {
        script {
          echo "deploying the docker image"
          withCredentials([usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            sh 'docker build -t kelz107/nana-projects:3.0 .'
            sh 'echo $PASS | docker login -u $USER --password-stdin'
            sh 'docker push kelz107/nana-projects:3.0'
          }
        }
      }
    }

    // deploying stage
    stage ("deploy"){
      steps{
        echo "deploying"
      }
    }
   
  }
  post {
    always {
      echo "Always running"
    }
    failure {
      echo "Failed build"
    }
  }

}