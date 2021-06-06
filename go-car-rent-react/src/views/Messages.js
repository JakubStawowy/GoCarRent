import React, {useEffect, useState} from 'react';
import {
    Container,
    List,
    Paper, Tab, Tabs, Typography
} from "@material-ui/core";

import Message from "../components/Message";
import {getAllUserMessages, getArchivedUserMessages, getUserMessages} from "../actions/actionRepository";
import {useMessagesStyles} from "../style/MessagesStyles";

export default function Messages() {
    const classes = useMessagesStyles();
    const [latestMessages, setLatestMessages] = useState([]);
    const [allMessages, setAllMessages] = useState([]);
    const [archivedMessages, setArchivedMessages] = useState([]);
    const [type, setType] = useState("unread");
    let index = 0;
    const flush = () => {
        getUserMessages(localStorage.getItem('userId'))
            .then((response) => setLatestMessages(response.data.reverse()))
            .catch((error) => alert(error));
        getAllUserMessages(localStorage.getItem('userId'))
            .then((response) => setAllMessages(response.data))
            .catch((error) => alert(error));
        getArchivedUserMessages(localStorage.getItem('userId'))
            .then((response)=>setArchivedMessages(response.data))
            .catch((error)=>alert(error));
    }

    useEffect(() => flush(), []);

    const emptySectionLabel = <Typography>This section is empty</Typography>
    return (
        <Container className={classes.container}>
            <Paper className={`${classes.list} ${classes.messages}`}>
                <Tabs>
                    <Tab onClick={()=>setType("unread")} label={"Unread messages"}/>
                    <Tab onClick={()=>setType("all")} label={"All messages"}/>
                    <Tab onClick={()=>setType("archived")} label={"Archived messages"}/>
                </Tabs>
                <React.Fragment>
                    <List>
                        {
                            type === "unread" &&
                            latestMessages.map(
                                message => {
                                    return (
                                        <Message
                                            index={index++}
                                            body={message}
                                            action={flush}
                                            archived={false}
                                        />
                                    )
                                }
                            )
                        }
                        {
                            type === 'unread' && latestMessages.length === 0 &&
                                emptySectionLabel
                        }
                        {
                            type === "all" &&
                            allMessages.map(
                                message => {
                                    return (
                                        <Message
                                            index={index++}
                                            body={message}
                                            action={flush}
                                            archived={false}
                                        />
                                    )
                                }
                            )
                        }
                        {
                            type === 'all' && allMessages.length === 0 &&
                            emptySectionLabel
                        }
                        {
                            type === "archived" &&
                            archivedMessages.map(
                                message => {
                                    return (
                                        <Message
                                            index={index++}
                                            body={message}
                                            action={flush}
                                            archived={true}
                                        />
                                    )
                                }
                            )
                        }
                        {
                            type === 'archived' && archivedMessages.length === 0 &&
                            emptySectionLabel
                        }
                    </List>
                </React.Fragment>
            </Paper>
        </Container>
    );
}