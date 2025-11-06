#!/user/bin/env groovy

@Library('jenkins-shared-library')
def gv

pipeline {
  agent any
  tools {
    maven 'maven-3.9'
  }
  stages {
    stage('init') {
      steps {
        script {
          gv = load 'script.groovy'
        }
      }
    }
    // building stage
    stage('test') {
      steps {
        script {
          gv.test()
        }
      }
    }

    // building
    stage('build java') {
      when {
        expression {
          env.BRANCH_NAME == 'main'
        }
      }
      steps {
        script {
          buildJavaApp()
        }
      }
    }

    stage('building and pushing docker image') {
      when {
        expression {
          env.BRANCH_NAME == 'main'
        }
      }
      steps {
        script {
          buildDockerImage 'kelz107/nana-projects:3.4'
          dockerLogin()
          dockerPush 'kelz107/nana-projects:3.4'
        }
      }
    }

    // deploying stage
    stage('deploy') {
      when {
        expression {
          env.BRANCH_NAME == 'main'
        }
      }
      steps {
        script {
          gv.deploy()
        }
      }
    }
  }
}
