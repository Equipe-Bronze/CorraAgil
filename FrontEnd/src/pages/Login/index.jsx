import { useNavigate } from 'react-router-dom'

import { BackButton, Container, ForgotPassword, Form, FormInputs, LoginAcess, Title, TopLogin } from "./styles"

import Button from '../../components/Button'
import Input from "../../components/Inputs"

import ImageLogin from '../../assets/topLogin.svg'
import Logo from '../../assets/corraAgil.png'


function Login() {

    const navigate = useNavigate()

    return (
        <Container>
            <LoginAcess>
                <TopLogin>
                    <img src={ImageLogin} alt="Imagem Pés Correndo" />
                </TopLogin>

                <img src={Logo} alt="Logo Corra Ágil" />

                <Form>

                    <Title>Login</Title>

                    <FormInputs>
                        <Input type="text" placeholder="Usuário"/>
                        <Input type="password" placeholder="Senha"/>
                    </FormInputs>
                </Form>

                <ForgotPassword>ESQUECEU A SENHA?</ForgotPassword>

                <BackButton>
                    <Button>ENTRAR</Button>
                    <Button theme="primary" onClick={() => navigate('/cadastro-usuarios')}>CADASTRAR</Button>
                </BackButton>

            </LoginAcess>
        </Container>
    )
}

export default Login