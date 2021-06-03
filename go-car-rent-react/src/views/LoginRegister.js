import {
    Button, Card, Grid, makeStyles,
    TextField, Typography
} from "@material-ui/core";

import {useDispatch} from "react-redux";
import {useEffect, useState} from "react";
import {loginUser, registerUser} from "../actions/actionRepository";
import {useHistory} from "react-router";
import {ERROR_CONFLICT, ERROR_NOT_FOUND} from "../data/errors";
import {useLoginRegisterStyles} from "../style/LoginRegisterStyles";
import {useValidatedStyles} from "../style/ValidatedStyles";

export default function LoginRegister(props) {
    const classes = useLoginRegisterStyles();
    const validationStyles = useValidatedStyles();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [registeredEmail, setRegisteredEmail] = useState('');
    const [registeredPassword, setRegisteredPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [emailCorrect, setEmailCorrect] = useState(true);
    const [passwordCorrect, setPasswordCorrect] = useState(true);
    const [confirmedPasswordCorrect, setConfirmedPasswordCorrect] = useState(true);
    const dispatch = useDispatch();
    const history = useHistory();

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        dispatch(loginUser({
            email: email,
            password: password
        })).then(() => {
            history.push('/home');
        }).catch((error) => {
            if (error.response !== undefined && error.response.status === ERROR_NOT_FOUND)
                alert("Bad email or password");
            else alert("Network error");
        });
    }

    const handleSubmitRegister = (e) => {
        e.preventDefault();
        emailCorrect && passwordCorrect && confirmedPasswordCorrect &&
            name.length !== 0 && surname.length !== 0 ?
        dispatch(registerUser({
            'email': registeredEmail,
            'password': registeredPassword,
            'userDetails': {
                'name': name,
                'surname': surname,
                'phone': null,
                'image': 'no-image'
            }
        })).then(() => {
            alert('User registered');
        }).catch((error) => {
            error.response !== undefined && error.response.status === ERROR_CONFLICT ?
                alert("User with this email already exists")
                :
                alert(error);
        })
            :
            alert("Wrong register user data");
    }

    const validateEmail = (email) => /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
    const validatePassword = (password) =>/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/.test(password);

    return (
        <Grid container className={classes.container} >
            <Grid item component={Card} className={classes.cards}>
                <Typography variant={'h4'} align={'center'}>
                    Login
                </Typography>
                <form className={classes.form} onSubmit={handleLoginSubmit}>
                    <TextField
                        name={"emailField"}
                        type={'text'}
                        label={'Email'}
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <TextField
                        name={"passwordField"}
                        type={'password'}
                        label={'Password'}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button
                        type={"submit"}
                        className={classes.button}
                        variant="contained"
                    >Login</Button>
                </form>
            </Grid>
            <Grid item component={Card} className={classes.cards}>
                <Typography variant={'h4'} align={'center'}>
                    Register
                </Typography>
                <form className={classes.form} onSubmit={handleSubmitRegister}>
                    <TextField type={'text'} label={'Name'} value={name} onChange={(e) => setName(e.target.value)}/>
                    <TextField type={'text'} label={'Surname'} value={surname} onChange={(e) => setSurname(e.target.value)}/>
                    <TextField
                        type={'text'}
                        label={'Email'}
                        value={registeredEmail}
                        className={emailCorrect ? validationStyles.correctTextField : validationStyles.wrongTextField}
                        onChange={(e) => {
                        setRegisteredEmail(e.target.value);
                        setTimeout(()=>setEmailCorrect(validateEmail(e.target.value)), 500);
                    }}/>
                    {
                        !emailCorrect &&
                            <Typography variant={'caption'}>
                                Bad email pattern
                            </Typography>
                    }
                    <TextField
                        type={'password'}
                        label={'Password'}
                        value={registeredPassword}
                        className={passwordCorrect ? validationStyles.correctTextField : validationStyles.wrongTextField}
                        onChange={(e) => {
                            setRegisteredPassword(e.target.value);
                            setTimeout(()=>setPasswordCorrect(validatePassword(e.target.value)),500);
                    }}/>
                    {
                        !passwordCorrect &&
                            <Typography variant={'caption'}>
                                Password must contain <br/> at least 8 characters <br/> (small letter, capital letter and number)
                            </Typography>
                    }
                    <TextField
                        type={'password'}
                        label={'Confirm password'}
                        value={confirmedPassword}
                        className={passwordCorrect ? validationStyles.correctTextField : validationStyles.wrongTextField}
                        onChange={(e) => {
                            setConfirmedPassword(e.target.value);
                            setTimeout(()=>setConfirmedPasswordCorrect(registeredPassword === e.target.value), 500);
                        }}
                    />
                    {
                        !confirmedPasswordCorrect &&
                        <Typography variant={'caption'}>
                            Passwords must match
                        </Typography>
                    }
                    <Button
                        className={classes.button}
                        variant="contained"
                        type={"submit"}
                    >Register</Button>
                </form>
            </Grid>
        </Grid>
    );
}