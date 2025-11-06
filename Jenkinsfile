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

    stage ('increment'){
      steps{
        script{
                    echo 'incrementing app version...'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"

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
      // when {
      //   expression {
      //     env.BRANCH_NAME == "main"
      //   }
      // }
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

    stage("commit version update"){
      steps{
        script{
          withCredentials([usernamePassword(credentialsId: 'kelz-github	', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            sh 'git config --global user.email "kmbawuike@gmail.com"'
            sh 'git config --global user.name "Kelechi Mbawuike"'
            
            sh "git remote set-url origin https://${USER}:${PASS}@https://github.com/kmbawuike/jenkins-java-maven-app.git"
            sh 'git add .'
            sh 'git commit -m "ci: version bump"'
            sh 'git push origin HEAD:feat/jenkins-jobs'
          }
        }
      }
    }
  }
}