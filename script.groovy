def test(){
   echo "testing ${env.BRANCH_NAME}"
  sh 'mvn test'
}
def buildJavaApp(){
  echo "building the application"
  sh 'mvn package'
}

def buildDockerImage(){
  echo "deploying the docker image"
  withCredentials([usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh 'docker build -t kelz107/nana-projects:3.0 .'
    sh 'echo $PASS | docker login -u $USER --password-stdin'
    sh 'docker push kelz107/nana-projects:3.0'
  }
}

def deploy(){
   echo "deploying the application"
}

return this