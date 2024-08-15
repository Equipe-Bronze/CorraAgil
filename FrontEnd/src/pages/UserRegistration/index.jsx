import { Input } from "../../components/Input";
import { Formik } from "formik";

export const initialValues = {
  name: "",
  email: "",
  password: "",
  password_confirmation: "",
};

function UserRegistration() {
  return (
    <div>
      <Formik initialValues={initialValues}>
        {() => <Input placeholder="Nome Completo" name="name" />}
      </Formik>
    </div>
  );
}

export default UserRegistration;
