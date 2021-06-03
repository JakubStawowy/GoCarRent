import {makeStyles} from "@material-ui/core";

export const useHomeStyles = makeStyles((theme) => ({
    list: {
        maxHeight: '85vh',
        marginTop: '2em',
        overflow: 'auto',

        [theme.breakpoints.down('xs')]: {
            maxHeight: '75vh',
        },
    },
    container: {
        marginTop: '2em',
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "space-around",
    },
    button: {
        background: '#4BBEBAE0',
        width: '40%',
    },
    searchingPanel: {
        background: '#4BBEBAE0',
        borderRadius: '30px',
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
        padding: '1em',
        width: '90%'
    },
    searchField: {
        padding: '0 2em 0em 2em',
    }
}));
