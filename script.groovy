def test() {
   echo "testing ${env.BRANCH_NAME}"
   sh 'mvn test'
}

def deploy() {
   echo 'deploying the application'
}

return this
