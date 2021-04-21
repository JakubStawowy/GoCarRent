import {
    Button, Card,
    Container,
    makeStyles,
    TextField, Typography
} from "@material-ui/core";

import {useDispatch, useSelector} from "react-redux";
import {useState} from "react";
import {loginUser} from "../actions/";
import {useHistory} from "react-router";

const useStyles = makeStyles({
    container: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-around',
        alignItems: 'center',
        height: '100%'
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
    }
});

export default function LoginRegister() {
    const classes = useStyles();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const history = useHistory();
    const isLogged = useSelector(state=>state.isLogged);
    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(loginUser({
            email: email,
            password: password
        })).then(() => {
            history.push('/home');
        });
    }

    const handleSubmitRegister = (e) => {
        e.preventDefault();
        alert(localStorage.getItem('token'));
    }

    return (
        <Container className={classes.container}>
            <Card className={classes.cards}>
                <Typography variant={'h4'} align={'center'}>
                    Login
                </Typography>
                <form className={classes.form} onSubmit={handleSubmit}>
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
            </Card>
            <Card className={classes.cards}>
                <Typography variant={'h4'} align={'center'}>
                    Register
                </Typography>
                <form className={classes.form} onSubmit={handleSubmitRegister}>
                    <TextField type={'text'} label={'Name'}/>
                    <TextField type={'text'} label={'Surname'}/>
                    <TextField type={'text'} label={'Email'}/>
                    <TextField type={'password'} label={'Password'}/>
                    <TextField type={'password'} label={'Confirm password'}/>
                    <Button
                        className={classes.button}
                        variant="contained"
                        type={"submit"}
                    >Register</Button>
                </form>
            </Card>
        </Container>
    );
}