#!/usr/bin/env groovy
library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
        [$class: 'GitSCMSource',
        remote: 'https://gitlab.com/twn-devops-bootcamp/latest/08-jenkins/jenkins-shared-library.git',
        credentialsId: 'gitlab-credentials'])

def gv

pipeline {   
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }

        stage("build and push image") {
            steps {
                script {
                    buildImage 'kelz107/nana-projects:jma-3.0'
                    dockerLogin()
                    dockerPush 'kelz107/nana-projects:jma-3.0'
                }
            }
        }
        
        stage("deploy") {
            steps {
                script {
                    gv.deploy()
                }
            }
        }               
    }
}
