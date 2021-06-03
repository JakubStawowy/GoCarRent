import {makeStyles} from "@material-ui/core";

export const useLeftSideBarStyles = makeStyles((theme) => ({
    bar: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '100%',
        position: 'relative',
        justifyContent: 'space-around',
        boxShadow: 'none',
        paddingLeft: '3em',
        [theme.breakpoints.down('xs')]: {
            // display: 'none',
            height: '88vh',
            position: 'fixed',
            width: '50%',
            padding: '0 1em 0 1em'
        }
    },
    button: {
        width: '100%',
        background: 'white',
        borderRadius: '30px 0 0 30px',
        color: '#4BBEBAE0',
        '&:hover': {
            color: 'white'
        },

        [theme.breakpoints.down('xs')]: {
            borderRadius: '30px'
        }
    },
    circle: {
        width: '10vw',
        height: '10vw',
        borderRadius: '50%',
        background: "white",
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    },
    logo: {
        width: '100%',
        height: '100%'
    },
    label: {
        [theme.breakpoints.down('sm')]: {
            display: 'none'
        }
    },
    root: {
        [theme.breakpoints.down('xs')]: {
            // display: 'none'
        }
    },
    navLink: {
        textDecoration: 'none'
    }
}));
