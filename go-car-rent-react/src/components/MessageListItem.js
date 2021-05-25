import {
    Avatar, Divider,
    ListItem,
    ListItemAvatar,
    ListItemText,
    makeStyles,
} from "@material-ui/core";
import image from '../uploads/user.png';
import React from "react";

const useStyles = makeStyles((theme) => ({
   container: {
       display: 'flex',
       justifyContent: 'flex-start'
   },
    content: {
       display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around',
        flex: 8
    },
    message: {
       overflow: "hidden"
    }
}));
function MessageListItem(props) {
    const classes = useStyles();
    return (
        <React.Fragment>
            <ListItem button selected={!props.displayed}>
                <ListItemAvatar>
                    <Avatar alt={'s'} src={image} />
                </ListItemAvatar>
                <ListItemText className={classes.message} primary={props.author} secondary={props.content}/>
            </ListItem>
            <Divider />
        </React.Fragment>
    );
}

export default MessageListItem;