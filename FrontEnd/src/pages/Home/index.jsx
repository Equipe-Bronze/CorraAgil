
import {
  BackHome,
  BottonPriority,
  Container,
  HomeAccess,
  LinkPriority,
  StartButton,
  Title,
  TopHome,
} from "./styles";

import ImageBackground from "../../assets/home-CorraAgil.svg";
import Logo from "../../assets/corraAgil.png";
import UserRegistration from "../UserRegistration";

function Home() {

import { useNavigate } from 'react-router-dom'

import { BackHome, BottonPriority, Container, HomeAccess, LinkPriority, Title, TopHome } from './styles'

import Button from '../../components/Button'

import ImageBackground from '../../assets/home-CorraAgil.svg'
import Logo from '../../assets/corraAgil.png'

function Home() {

  const navigate = useNavigate()


  return (
    <Container>
      <HomeAccess>
        <TopHome>
          <img src={ImageBackground} alt="Imagem Background Home" />
        </TopHome>

        <BackHome>
          <img src={Logo} alt="Logo CorraAgil" />

          <UserRegistration />

          <h2>Pronto para acelerar?</h2>
        </BackHome>

        <Title>Faça login e comece sua corrida!</Title>

        <Button theme="primary" onClick={() => navigate('/login')}>COMEÇAR</Button>

        <BottonPriority>
          Para proteger sua privacidade e garantir a segurança de suas
          informações pessoais, ao continuar, você concorda com a:
        </BottonPriority>

        <LinkPriority>Política de privacidade</LinkPriority>
      </HomeAccess>
    </Container>
  );
}

export default Home;
