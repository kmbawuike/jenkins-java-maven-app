pipeline {
  agent any 
  stages {
    stage ("build"){
      steps {
        echo 'building application'
      }
    }
    stage("test"){
      when {
        expression {
          BRANCH_NAME === "main" || BRANCH_NAME === "dev"
        }
      }
      steps{
        echo "testing application"
      }
    }
    stage ("deploy"){
      steps {
        echo "deploying application"
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