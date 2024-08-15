import styled from "styled-components";
const getColor = (variant) => {
    switch (variant) {
        case 'primary':
            return '#000';
        case 'secondary':
            return '#000';
            case 'tertiary':
            return '#fff';
        default:
            return '#000'; // Cor padrão
    }
};

const getBackgroundColor = (variant) => {
    switch (variant) {
        case 'primary':
            return '#fff';
        case 'secondary':
            return '#FFA500';
        case 'tertiary':
            return '#3C5A9A';
        default:
            return '#fff'; // Cor padrão
    }
};

export const StartButton = styled.button`
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    color: ${({ variant }) => getColor(variant)};
    background-color: ${({ variant }) => getBackgroundColor(variant)};
    border: none;
    height: 56px;
    width: 372px;
    border-radius: 20px;
    font-size: 20px;
    font-weight: bold;
    cursor: pointer;
    
    &:hover {
        opacity: 0.8;
    }

    &:active {
        opacity: 0.5;
    }
`;
