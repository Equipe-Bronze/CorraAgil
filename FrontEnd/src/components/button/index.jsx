import PropTypes from "prop-types";
import { StartButton } from "./styles";

export const Button = ({ children, variant }) => {
  return <StartButton variant={variant}>{children}</StartButton>;
};

Button.propTypes = {
  children: PropTypes.node.isRequired,
  variant: PropTypes.oneOf(["primary", "secondary", "tertiary"]),
};
