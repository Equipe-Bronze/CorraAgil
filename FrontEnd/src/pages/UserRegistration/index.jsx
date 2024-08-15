import { Input } from "../../components/Input";
import { Formik } from "formik";
import { Container, HomeAccess, Title } from "../Home/styles";
import Logo from "../../assets/corraAgil.png";
import { FormContainer, ButtonContainer } from "./styles";
import { Button } from "../../components/button";
import { FaArrowLeft } from "react-icons/fa";

export const initialValues = {
  name: "",
  email: "",
  password: "",
  password_confirmation: "",
};

function UserRegistration() {
  return (
    <Container>
      <HomeAccess>
        <img src={Logo} alt="Logo CorraAgil" />
        <Title>Criar uma conta</Title>
        <Formik initialValues={initialValues}>
          {() => (
            <FormContainer>
              <Input placeholder="Nome Completo" name="name" />
              <Input placeholder="E-mail" name="email" />
              <Input placeholder="Senha" name="password" />
              <Input
                placeholder="Confirmar Senha"
                name="password_confirmation"
              />

              <ButtonContainer>
                <Button variant="secondary">CONFIRMAR</Button>
                <Button variant="tertiary">
                  <img src="src/assets/face.svg" alt="logo facebook" />
                  Entrar com Facebook
                </Button>
                <Button variant="primary">
                  <img src="src/assets/google.svg" alt="logo google" />
                  Entrar com Google
                </Button>
              </ButtonContainer>
              <FaArrowLeft size="38px" />
            </FormContainer>
          )}
        </Formik>
      </HomeAccess>
    </Container>
  );
}

export default UserRegistration;
