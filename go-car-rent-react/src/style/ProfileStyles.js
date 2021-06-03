import {makeStyles} from "@material-ui/core";

export const useProfileStyles = makeStyles((theme) => ({

    container: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100%'
    },
    img: {
        width: '20%',
        borderRadius: '50%'
    },
    list: {
        maxHeight: '30vh',
        overflow: 'auto',
        width: '100%'
    },
    button: {
        background: '#4BBEBAE0',
        color: 'white'
    }
}));