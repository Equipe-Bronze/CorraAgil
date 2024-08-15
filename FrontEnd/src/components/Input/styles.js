import styled from "styled-components";

export const Input = styled.div`
    min-width: 346px;
    min-height: 50px;
    border-radius: 8px;
    display: flex;
    padding: 10px;
    background-color: #ffffff;

    input {
    border: none;
    flex:1;
    color: #000000;
    background-color: transparent;
    

    &:focus {
      outline: none;
      text-align: center;
    }

    &:placeholder-shown {
      color: rgb(0,0,0,50%);
      background-color: #ffffff;
      text-align: center;
    }
}

`