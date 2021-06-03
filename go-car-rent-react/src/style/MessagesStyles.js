import {makeStyles} from "@material-ui/core";

export const useMessagesStyles = makeStyles((theme) => ({
    container: {
        marginTop: '1em',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around'
    },
    list: {
        // maxHeight: '40vh',
        overflow: "auto"
    },
    chat: {
        flex: 6,
        padding: '0 1em',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'flex-end',
        [theme.breakpoints.down('sm')]: {
            display:'none'
        }
    },
    messages: {
        flex: 4,
    }
}));