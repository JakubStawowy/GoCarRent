import {makeStyles} from "@material-ui/core";

export const useLoginRegisterStyles = makeStyles((theme)=>({
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