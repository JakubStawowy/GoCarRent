import React from 'react';
import '../components/components.css';
import {
    Container,
    List,
    ListItemText,
    makeStyles,
    Paper
} from "@material-ui/core";

import messages from "../data/messages";
import MessageListItem from "../components/MessageListItem";

const useStyles = makeStyles((theme) => ({
    container: {
        marginTop: '1em',
        display: 'flex',
        justifyContent: 'space-around'
    },
    list: {
        maxHeight: '85vh',
        overflow: "auto"
    },
    chat: {
        flex: 6,
        padding: '0 1em',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'flex-end'
    },
    messages: {
        flex: 4,
    }
}));

export default function Messages() {
    const classes = useStyles();
    const messagesData = messages;
    return (
        <Container className={classes.container}>
            <Paper className={`${classes.list} ${classes.messages}`}>
                <React.Fragment>
                    <List>
                        {
                            messagesData.map(
                                message => {
                                    return (
                                        <MessageListItem
                                            author={message.author}
                                            content={message.content}
                                            date={message.date}
                                            displayed={message.displayed}
                                        />
                                    )
                                }
                            )
                        }
                    </List>
                </React.Fragment>
            </Paper>
            <Paper className={`${classes.list} ${classes.chat}`}>
                <List className={classes.list}>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText primary={"Hello"} secondary={"12:00"}/>
                    <ListItemText align={'right'} primary={"Hello"} secondary={"12:00"}/>
                </List>
            </Paper>
        </Container>
    );
}