import useGlobalState from "../store/store"
import { getFeed } from "../services/FeedService";

export default function Feed(){
    const {access_token} = useGlobalState();
    const feed = getFeed(access_token);    

    return <h1>Feed</h1>
}
