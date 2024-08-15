import styled from 'styled-components';

export const Container = styled.div`
    background-color: #fff;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
    height: 100vh;
`
export const LoginAcess = styled.div`
    background-color: #12263A;
    height: 932px;
    width: 430px;

    display: flex;
    align-items: center; 
    flex-direction: column;

    img{
        justify-content: center;
    }
`

export const TopLogin = styled.div`
    height: 308px;
    width: 430px;
    margin-bottom: 20px;

    img{
        max-height: 308px;
        max-width: 430px;
    }
`

export const Form = styled.form`
    display: flex;
    flex-direction: column;
`

export const Title = styled.h2`
    font-size: 32px;
    font-weight: bold;
    text-align: center;
    margin: 40px 0 23px;
`

export const FormInputs = styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;

    gap: 20px;
`

export const ForgotPassword = styled.a`
    font-size: 20px;
    font-weight: 5px;
    margin: 20px 0 33px 0;
    cursor: pointer;

    &:hover{
        opacity: 0.8;
    }

    &:active{
        opacity: 0.5;
    }
`

export const BackButton = styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;
    gap: 20px;
`