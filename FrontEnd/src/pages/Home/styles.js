import styled from 'styled-components';

export const Container = styled.div`
    background-color: #fff;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
    height: 100vh;
`
export const HomeAccess = styled.div`
    background-color: #12263A;
    height: 932px;
    width: 430px;

    display: flex;
    align-items: center; 
    justify-content: center;
    flex-direction: column;
`

export const TopHome = styled.div`
    height: 559px;
    width: 430px;

    position: relative;

    img{
        max-height: 100vh;
        max-width: 100%;
    }

`

export const BackHome = styled.div`
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    gap: 20px;

    img{
        
    }

    h2{

    }

`

export const Title = styled.h1`
    font-size: 32px;
    width: 330px;
    text-align: center;
    margin: 36px 0 40px 0;
`

export const BottonPriority = styled.h4`
    font-size: 12px;
    width: 335px;
    text-align: center;
    margin: 40px 0 8px;
`

export const LinkPriority = styled.a`
    font-size: 13px;
    font-weight: bold;
    margin-bottom: 84px;
    cursor: pointer;

    &:hover{
        opacity: 0.8;
    }

    &:active{
        opacity: 0.5;
    }
`