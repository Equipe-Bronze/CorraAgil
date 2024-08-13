import styled from "styled-components";

export const Button = styled.button`
    color: #000;
    background: ${ props => props.theme === 'primary' ? '#fff' : '#ffa500'};
    border: none;
    height: 56px;
    width: 372px;
    border-radius: 20px;
    font-size: 20px;
    font-weight: bold;
    cursor: pointer;
    
    &:hover{
        opacity: 0.8;
    }

    &:active{
        opacity: 0.5;
    }
`