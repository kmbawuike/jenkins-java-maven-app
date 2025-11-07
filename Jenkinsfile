import java.net.URLEncoder

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

  stage("Commit version update") {
      steps {
          script {
            withCredentials([usernamePassword(credentialsId: 'kelz-github', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
    def safePass = URLEncoder.encode(PASS, "UTF-8")
    sh """
        git config --global user.email "kmbawuike@gmail.com"
        git config --global user.name "kmbawuike"
        git remote set-url origin https://${USER}:${safePass.replace("@", "%40")}@github.com/kmbawuike/jenkins-java-maven-app.git
        git add .
        git commit -m "ci: version bump" || echo "No changes"
        git push origin HEAD:feat/jenkins-jobs
    """
}
          }
      }
  }

  }
}