pipeline {
  agent any 
  tools {
    maven 'maven-3.9'
  }
  stages {
    stage("init"){
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    }
    // building stage
    stage ("build jar"){
      steps {
        script {
        gv.buildJar()
        }
      }
    }

    // building stage
    stage ("build"){
      steps {
        script {
        gv.build()
        }
      }
    }

    // deploying stage
    stage ("deploy"){
      steps{
        script {
          gv.deploy()
        }
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