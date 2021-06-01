import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {
    Container,
    List,
    makeStyles,
    Paper, Typography
} from "@material-ui/core";

import Message from "../components/Message";
import {getAllUserMessages, getUserMessages} from "../actions/actionRepository";

const useStyles = makeStyles((theme) => ({
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

export default function Messages() {
    const classes = useStyles();
    const [latestMessages, setLatestMessages] = useState([]);
    const [allMessages, setAllMessages] = useState([]);
    let index = 0;
    useEffect(() => {
            getUserMessages(localStorage.getItem('userId'))
                .then((response) => setLatestMessages(response.data))
                .catch((error) => alert(error));
            getAllUserMessages(localStorage.getItem('userId'))
                .then((response) => setAllMessages(response.data))
                .catch((error) => alert(error));
        }
    , [])


    const closeMessage = (messageIndex) => {
        latestMessages.splice(messageIndex, 1);
    }

    return (
        <Container className={classes.container}>
            <Paper className={`${classes.list} ${classes.messages}`}>
                <Typography>
                    Latest messages
                </Typography>
                <React.Fragment>
                    <List>
                        {
                            latestMessages.map(
                                message => {
                                    return (
                                        <Message
                                            index={index++}
                                            body={message}
                                            action={closeMessage}
                                        />
                                    )
                                }
                            )
                        }
                    </List>
                </React.Fragment>
            </Paper>
            <Paper className={`${classes.list} ${classes.messages}`}>
                <Typography>
                    Unread messages
                </Typography>
                <React.Fragment>
                    <List>
                        {
                            allMessages.map(
                                message => {
                                    return (
                                        <Message
                                            index={index++}
                                            body={message}
                                            action={closeMessage}
                                        />
                                    )
                                }
                            )
                        }
                    </List>
                </React.Fragment>
            </Paper>
        </Container>
    );
}