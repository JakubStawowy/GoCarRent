import {
    Button, Card,
    Container,
    FormControl,
    makeStyles,
    TextField, Typography
} from "@material-ui/core";
import logo from '../uploads/graylogo2.png';

const useStyles = makeStyles({
    container: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-around',
        alignItems: 'center',
        // marginTop: '2em'
        height: '100%'
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        padding: '1em',
    },
    button: {
        // background: '#4BBEBAE0',
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
    return (
        <Container className={classes.container}>
            <img className={classes.logo} src={logo} alt={''}/>
            <Card className={classes.cards}>
                <Typography variant={'h4'} align={'center'}>
                    Login
                </Typography>
                <FormControl className={classes.form} variant={'filled'}>
                    <TextField type={'text'} label={'Email'}/>
                    <TextField type={'password'} label={'Password'}/>
                    <Button
                        className={classes.button}
                        variant="contained"
                    >Login</Button>
                </FormControl>
            </Card>
            <Card className={classes.cards}>
                <Typography variant={'h4'} align={'center'}>
                    Register
                </Typography>
                <FormControl className={classes.form}>
                    <TextField type={'text'} label={'Name'}/>
                    <TextField type={'text'} label={'Surname'}/>
                    <TextField type={'text'} label={'Email'}/>
                    <TextField type={'password'} label={'Password'}/>
                    <TextField type={'password'} label={'Confirm password'}/>
                    <Button
                        className={classes.button}
                        variant="contained"
                    >Register</Button>
                </FormControl>
            </Card>
        </Container>
    );
}