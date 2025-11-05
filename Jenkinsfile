#!/user/bin/env groovy
@Library('jenkins-shared-library')
def gv

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
    stage ("test"){
      steps {
        script {
        gv.test()
        }
      }
    }

    // building stage
    stage ("build java app"){
      when {
        expression {
          env.BRANCH_NAME == "main"
        }
      }
      steps {
        script {
        buildJar()
        }
      }
    }

    // building stage
    stage ("build docker image"){
      when {
        expression {
          env.BRANCH_NAME == "main"
        }
      }
      steps {
        script {
        buildImage 'kelz107/nana-projects:4.0'
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