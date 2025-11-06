#!/user/bin/env groovy
// @Library('jenkins-shared-library') // shared library defined globally in jenkins

library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
  [
    $class: 'GitSCMSource', 
    remote: 'https://github.com/kmbawuike/jenkins-shared-library.git',
    credentialsId: 'kelz-github'])

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
