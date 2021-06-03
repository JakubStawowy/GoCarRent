import {Avatar, Card, Container, makeStyles, Typography} from "@material-ui/core";
import image from '../uploads/user.png';
import StarIcon from '@material-ui/icons/Star';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import {useHistory} from "react-router";
import {useFeedbackStyles} from "../style/FeedbackStyles";

export default function Feedback(props) {

    /*  Hooks   */
    const classes = useFeedbackStyles();
    const history = useHistory();

    let starsArray = [];
    for(let i = 0 ; i < 5; i++) {
        i <= props.stars ? starsArray[i] = true : starsArray[i] = false;
    }

    const handleAuthorClick = () => {
        history.replace('/users/' + props.authorId + '/profile');
    }

    return (
        <Card className={classes.paper}>
            <Avatar src={image} alt={''} className={classes.img} />
            <Container className={classes.content} style={{padding: '1em'}}>
                <Typography className={classes.feedbackTop}>
                    <Typography variant={'h6'} onClick={handleAuthorClick} className={classes.feedbackAuthorLabel}>
                        {props.author}
                    </Typography>
                    <div className={classes.stars}>
                        {
                            starsArray.map(star => {
                                if(star)
                                    return (
                                        <StarIcon />
                                    )
                                else
                                    return (
                                        <StarBorderIcon />
                                    )
                            })
                        }
                    </div>
                </Typography>
                <Typography variant={'body1'}>
                    {props.content}
                </Typography>
            </Container>
        </Card>
    )
}