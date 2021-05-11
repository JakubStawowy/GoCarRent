import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {Button, Container, List, ListItem, makeStyles, Typography} from "@material-ui/core";
import userImage from '../uploads/user.png';
import Feedback from "../components/Feedback";
import {getUserDetails} from "../actions/getUserDetails";
import {useHistory} from "react-router";
import {getFeedback} from "../actions/getFeedback";
import {ERROR_FORBIDDEN} from "../data/errors";
const useStyles = makeStyles((theme) => ({

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

export default function Profile() {

    const [userData, setUserData] = useState({
        name: '',
        surname: ''
    });
    const [feedback, setFeedback] = useState([]);

    const classes = useStyles();
    const history = useHistory();

    const handleError = (error) => {
        if(error.response.status === ERROR_FORBIDDEN) {
            localStorage.clear();
            history.replace("/login");
        }
    }

    useEffect(  () => {
        getUserDetails().then(response => {
           setUserData(response.data);
        }).catch((error) => handleError(error));

        getFeedback(localStorage.getItem("userId")).then(
            (response) => setFeedback(response.data)
        ).catch((error) => handleError(error));
    }, []);

    return (
        <Container className={classes.container}>
            <img className={classes.img} src={userImage} alt={''}/>
            <Typography variant={'h3'} gutterBottom={'true'}>
                {`${userData.name} ${userData.surname}`}
            </Typography>
            <Typography gutterBottom={'true'}>
                <Button variant={'contained'} className={classes.button}>User announcements</Button>
            </Typography>
            <Typography variant={'h4'} gutterBottom={'true'}>
                Feedback
            </Typography>
            <List className={classes.list}>
                {feedback.length !== 0 ? feedback.map(
                    singleFeedback => {
                        return (
                            <ListItem>
                                <Feedback
                                    author={singleFeedback.author}
                                    content={singleFeedback.content}
                                    stars={singleFeedback.rate}
                                />
                            </ListItem>
                        )
                    }
                )
                    :
                    <Typography variant={'h5'} gutterBottom={'true'}>
                        {userData.name} does not have feedback yet
                    </Typography>
                }
            </List>
        </Container>
    );
}