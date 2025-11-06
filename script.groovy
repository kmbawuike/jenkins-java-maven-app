def test(){
   echo "testing ${env.BRANCH_NAME}"
  sh 'mvn test'
}

def build(){
  echo "building the application"
  sh 'mvn clean package'
  echo "deploying the docker image"
  withCredentials([usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh "docker build -t kelz107/nana-projects:${env.IMAGE_NAME} ."
    sh 'echo $PASS | docker login -u $USER --password-stdin'
    sh "docker push kelz107/nana-projects:${env._IMAGE_NAME}"
  }
}

def deploy(){
   echo "deploying the application"
}

return this