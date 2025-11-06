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
    stage ("test......"){
      steps {
        script {
        gv.test()
        }
      }
    }

    // building stage
    stage ("build"){
      when {
        expression {
          env.BRANCH_NAME == "main"
        }
      }
      steps {
        script {
        gv.build()
        }
      }
    }

    // deploying stage
    stage ("deploy"){
      when {
        expression {
          env.BRANCH_NAME == "main"
        }
      }
      steps{
        script {
          gv.deploy()
        }
      }
    }
  }
}