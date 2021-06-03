import {makeStyles} from "@material-ui/core";

export const useAnnouncementFormStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        justifyContent: 'space-around',

        [theme.breakpoints.down('xs')]: {
            height: '88vh'
        }
    },
    form: {
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
    },
    item: {
        width: '90%'
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
    button: {
        background: '#4BBEBAE0',
    },
    deleteButton: {
        background: '#FA8072'
    },
    titleField: {
        marginTop: '2em'
    },
    confirmArea: {
        borderRadius: '2em',
        background: '#FA8072',
        display: 'flex',
        justifyContent: 'center',
    },
    confirmField: {
        background: '#FCA294',
        width: '40%',
    }
}));