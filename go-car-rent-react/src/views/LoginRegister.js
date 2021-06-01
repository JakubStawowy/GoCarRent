import {
    Button, Card, Grid,
    makeStyles,
    TextField, Typography
} from "@material-ui/core";

import {useDispatch} from "react-redux";
import {useState} from "react";
import {loginUser, registerUser} from "../actions/actionRepository";
import {useHistory} from "react-router";
import {ERROR_NOT_FOUND} from "../data/errors";

const useStyles = makeStyles((theme)=>({
    container: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-around',
        alignItems: 'center',
        height: '100%',

        [theme.breakpoints.down('xs')]: {
            maxHeight: "86vh",
            overflow: 'auto'
        }
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        padding: '1em',
    },
    button: {
        marginTop: '1em'
    },
    logo: {
        position: 'fixed',
        opacity: '0.2',
        width: '60%'
    },
    cards: {
        opacity: '1',
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
    },
    list: {
        maxHeight: '100vh'
    }
}));

export default function LoginRegister() {
    const classes = useStyles();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [registeredEmail, setRegisteredEmail] = useState('');
    const [registeredPassword, setRegisteredPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
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
            alert(error);
        });
    }

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
                    <TextField type={'text'} label={'Email'} value={registeredEmail} onChange={(e) => setRegisteredEmail(e.target.value)}/>
                    <TextField type={'password'} label={'Password'} value={registeredPassword} onChange={(e) => setRegisteredPassword(e.target.value)}/>
                    <TextField type={'password'} label={'Confirm password'} value={confirmedPassword} onChange={(e) => setConfirmedPassword(e.target.value)}/>
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