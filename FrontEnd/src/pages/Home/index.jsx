import { BackHome, BottonPriority, Container, HomeAccess, LinkPriority, StartButton, Title, TopHome } from './styles'

import ImageBackground from '../../assets/home-CorraAgil.svg'
import Logo from '../../assets/corraAgil.png'

function Home() {

  return (
    <Container>

      <HomeAccess>

        <TopHome>
          <img src={ImageBackground} alt="Imagem Background Home" />
        </TopHome>

        <BackHome>

        <img src={Logo} alt="Logo CorraAgil" />

        <h2>Pronto para acelerar?</h2>

        </BackHome>

        <Title>Faça login e comece sua corrida!</Title>

        <StartButton>COMEÇAR</StartButton>

        <BottonPriority>Para proteger sua privacidade e garantir a segurança de suas informações pessoais, ao continuar, você concorda com a:</BottonPriority>

        <LinkPriority>Política de privacidade</LinkPriority>

      </HomeAccess>


    </Container>
  )
}

export default Home