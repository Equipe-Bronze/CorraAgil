import { Field } from "formik";
import PropTypes from "prop-types";
import * as S from "./styles";

export const Input = ({ name, type, placeholder }) => {
  return (
    <S.Input>
      <Field name={name} type={type} placeholder={placeholder} />
    </S.Input>
  );
};

Input.propTypes = {
  name: PropTypes.string.isRequired,
  type: PropTypes.string.isRequired,
  placeholder: PropTypes.string,
};
