import {Field} from 'formik';
import PropTypes from 'prop-types';

export const Input = ({id, name, type, placeholder}) =>{
    return(
        <div>
            <Field id={id} name={name} type={type} placeholder={placeholder}/>
        </div>
    )
}

Input.propTypes = {
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    placeholder: PropTypes.string,
  };