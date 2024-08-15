import PropTypes from "prop-types";
import { StartButton } from "./styles";

export const Button = ({ children }) => {
  return <StartButton>{children}</StartButton>;
};

Button.propTypes = {
  children: PropTypes.node.isRequired,
};
