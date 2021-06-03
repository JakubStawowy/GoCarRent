import {makeStyles} from "@material-ui/core";

export const useRightSideBarStyles = makeStyles((theme) => ({
    bar: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '100%',
        position: 'relative',
        justifyContent: 'space-around',
        boxShadow: 'none',
        paddingRight: '3em',
        [theme.breakpoints.down('xs')]: {
            // display: 'none'
            height: '88vh',
            position: 'fixed',
            right: 0,
            width: '50%',
            padding: '0 1em 0 1em'
        }
    },
    button: {
        background: 'white',
        width: '100%',
        borderRadius: '0 30px 30px 0',
        color: '#4BBEBAE0',
        '&:hover': {
            color: 'white'
        },

        [theme.breakpoints.down('xs')]: {
            borderRadius: '30px'
        }
    },
    circlePlate: {
        display: "flex",
        justifyContent: "flex-end"
    },
    circle: {
        width: '10vw',
        height: '10vw',
    },
    img: {
        borderRadius: '50%',
        width: '100%',
        objectFit: 'cover'
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
    }
}));
