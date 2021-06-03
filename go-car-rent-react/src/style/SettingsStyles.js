import {makeStyles} from "@material-ui/core";

export const useSettingsStyles = makeStyles((theme) => ({
    container: {
        height: '100%',
        width: '100%',
        // marginTop: '2em',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around'
    },
    form: {
        width: '100%',
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
    },
    item: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexBasis: '25%'
    },
    input: {
        height: '25%',
        width: '60%'
    },
    button: {
        background: '#4BBEBAE0',
        width: '40%',
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
}));